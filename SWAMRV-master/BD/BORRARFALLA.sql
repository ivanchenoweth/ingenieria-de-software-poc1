--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARFALLA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARFALLA" (
v_idfalla IN falla.id_falla%type
)

is
BEGIN

DELETE FROM FALLA WHERE id_falla = v_idfalla;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar la Falla' || v_idfalla || ' . Operacion interrumpida.');
rollback;
end;

/
