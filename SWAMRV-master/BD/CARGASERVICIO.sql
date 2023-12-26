--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGASERVICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGASERVICIO" (
v_nombre IN servicio.nombre%type,
v_idservicio OUT servicio.id_servicio%type,
v_descripcion OUT servicio.descripcion%type
)

as
BEGIN
SELECT id_servicio, descripcion
INTO v_idservicio, v_descripcion
FROM SERVICIO WHERE NOMBRE = v_nombre;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('SERVICIO no encontrado en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar el servicio '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
