--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRAROT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRAROT" (
v_idot IN orden_trabajo.id_ot%type,
v_patente IN orden_trabajo.patente%type
)

is
BEGIN

DELETE FROM ORDEN_TRABAJO
WHERE PATENTE = v_patente
AND ID_OT = v_idot;


commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar la orden de trabajo ' || v_idot || '  del vehiculo ' || v_patente || ' . Operacion interrumpida.');
rollback;
end;

/
