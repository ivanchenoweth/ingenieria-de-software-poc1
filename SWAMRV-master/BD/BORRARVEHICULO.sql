--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARVEHICULO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARVEHICULO" (
v_patente IN vehiculo.patente%type
)

is
BEGIN
DELETE FROM VEHICULO WHERE PATENTE = v_patente;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar al intentar eliminar el vehiculo vehiculo' || v_patente || ' . Operacion interrumpida.');
rollback;
end;

/
