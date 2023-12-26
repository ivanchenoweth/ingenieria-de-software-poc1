--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARREPUESTOOT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARREPUESTOOT" (
v_idot IN orden_trabajo_repuesto.id_ot%type,
v_patente IN orden_trabajo_repuesto.patente%type,
v_idrepuesto IN orden_trabajo_repuesto.id_repuesto%type
)
is
v_cantidad number := 0;
BEGIN
SELECT CANTIDAD INTO v_cantidad
FROM ORDEN_TRABAJO_REPUESTO 
WHERE ID_REPUESTO = v_idrepuesto
AND PATENTE = v_patente
AND ID_OT = v_idot;

UPDATE REPUESTO
set CANTIDAD = cantidad + v_cantidad
WHERE ID_REPUESTO = v_idrepuesto;

DELETE FROM ORDEN_TRABAJO_REPUESTO 
WHERE ID_REPUESTO = v_idrepuesto
AND PATENTE = v_patente
AND ID_OT = v_idot;


commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar el Repuesto ' || v_idrepuesto || ' . Operacion interrumpida.');
rollback;
end;

/
