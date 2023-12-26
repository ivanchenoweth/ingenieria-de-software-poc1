--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARREPUESTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARREPUESTO" (
v_id_repuesto repuesto.id_repuesto%type,
v_nombre repuesto.nombre%type,
v_descripcion repuesto.descripcion%type,
v_foto repuesto.foto%type)

is
BEGIN

UPDATE REPUESTO
SET nombre = v_nombre,
descripcion = v_descripcion,
foto = v_foto
WHERE id_repuesto = v_id_repuesto;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar el Repuesto '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
