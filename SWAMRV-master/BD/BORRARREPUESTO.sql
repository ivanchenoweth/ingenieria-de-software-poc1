--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARREPUESTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARREPUESTO" (
v_idrepuesto IN repuesto.id_repuesto%type
)

is
BEGIN

DELETE FROM REPUESTO 
where id_repuesto = v_idrepuesto;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar el Repuesto ' || v_idrepuesto || ' . Operacion interrumpida.');
rollback;
end;

/
