
  CREATE OR REPLACE PROCEDURE "SYSTEM"."BORRARCONDUCTORES" (
v_rut conductor.rut_conductor%type)
as
BEGIN
DELETE
FROM CONDUCTOR WHERE RUT_CONDUCTOR = v_rut;
commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('RUT Ya existe en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al borrar al Conductor '||v_rut||'. Operacion interrumpida.');
rollback;
end;
/
 
