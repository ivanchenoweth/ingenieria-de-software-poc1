--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARSERVICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARSERVICIO" (
v_idservicio servicio.id_servicio%type,
v_nombre servicio.nombre%type,
v_descripcion servicio.descripcion%type
)

is
BEGIN
UPDATE SERVICIO
set nombre = v_nombre,
descripcion = v_descripcion
WHERE id_servicio = v_idservicio;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar el servicio '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
