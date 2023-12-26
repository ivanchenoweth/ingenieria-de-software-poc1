--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARFALLAOT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARFALLAOT" (
v_idot IN orden_trabajo_falla.id_ot%type,
v_patente IN orden_trabajo_falla.patente%type,
v_idfalla IN orden_trabajo_falla.id_falla%type
)
is
BEGIN

DELETE FROM ORDEN_TRABAJO_FALLA
WHERE ID_FALLA = v_idfalla
AND PATENTE = v_patente
AND ID_OT = v_idot;


commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar la falla ' || v_idfalla || ' . Operacion interrumpida.');
rollback;
end;

/
