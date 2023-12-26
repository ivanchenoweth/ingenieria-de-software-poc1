--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARCOMPRA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARCOMPRA" (
v_nrofactura IN compra_detalle.nro_factura%type
)

is
BEGIN

DELETE FROM COMPRA
WHERE nro_factura = v_nrofactura;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar la compra' || v_nrofactura || ' . Operacion interrumpida.');
rollback;
end;

/
