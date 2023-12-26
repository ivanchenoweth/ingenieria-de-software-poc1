--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BORRARREPUESTOCOMPRA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BORRARREPUESTOCOMPRA" (
v_nrofactura IN compra_detalle.nro_factura%type,
v_idrepuesto IN compra_detalle.id_repuesto%type
)
is

v_cantidad number := 0;

BEGIN

SELECT cantidad into v_cantidad
FROM COMPRA_DETALLE WHERE id_repuesto = v_idrepuesto
and nro_factura = v_nrofactura;

UPDATE REPUESTO
set cantidad = cantidad - v_cantidad
where id_repuesto = v_idrepuesto;

DELETE FROM COMPRA_DETALLE
WHERE nro_factura = v_nrofactura
and id_repuesto = v_idrepuesto;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos que desea eliminar');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar eliminar la compra' || v_nrofactura || ' . Operacion interrumpida.');
rollback;
end;

/
