--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAFALLAS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAFALLAS" (
v_nombre IN falla.nombre%type,
v_id_falla OUT falla.id_falla%type,
v_descripcion OUT falla.descripcion%type,
v_foto OUT falla.foto%type)

as
BEGIN
SELECT id_falla, descripcion, foto
INTO v_id_falla, v_descripcion, v_foto
FROM FALLA WHERE NOMBRE = v_nombre;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('FALLA no encontrada en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar la falla '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
