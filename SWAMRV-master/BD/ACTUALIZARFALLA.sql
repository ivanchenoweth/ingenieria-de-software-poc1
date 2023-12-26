--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARFALLA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARFALLA" (
v_id_falla falla.id_falla%type,
v_nombre falla.nombre%type,
v_descripcion falla.descripcion%type,
v_foto falla.foto%type)

is
BEGIN
UPDATE FALLA
set nombre = v_nombre,
descripcion = v_descripcion,
foto = v_foto
WHERE id_falla = v_id_falla;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar la falla '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
