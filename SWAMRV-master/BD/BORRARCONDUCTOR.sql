--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARCONDUCTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARCONDUCTOR" (
v_rut IN conductor.rut_conductor%type
)

is
BEGIN
DELETE FROM CONDUCTOR WHERE rut_conductor = v_rut;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar el conductor' || v_rut || ' . Operacion interrumpida.');
rollback;
end;

/
