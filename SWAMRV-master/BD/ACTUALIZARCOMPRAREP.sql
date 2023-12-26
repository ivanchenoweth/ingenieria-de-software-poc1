--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARCOMPRAREP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARCOMPRAREP" (
v_nrofactura compra_detalle.nro_factura%type,
v_idrepuesto compra_detalle.id_repuesto%type,
v_cantidad compra_detalle.cantidad%type,
v_detalle compra_detalle.detalle%type
)
is
cantidad number := 0;
cantidad2 number := 0;

BEGIN
SELECT cantidad into cantidad
FROM REPUESTO where id_repuesto = v_idrepuesto;

SELECT cantidad into cantidad2
FROM COMPRA_DETALLE where id_repuesto = v_idrepuesto
and nro_factura = v_nrofactura;

UPDATE COMPRA_DETALLE
set cantidad =  v_cantidad,
detalle = v_detalle
where nro_factura = v_nrofactura
and id_repuesto = v_idrepuesto;

UPDATE REPUESTO SET CANTIDAD = cantidad+v_cantidad-cantidad2
where id_repuesto = v_idrepuesto;

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Numero de factura y Repuesto ya existente en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la compra '||v_nrofactura||' del repuesto '||v_idrepuesto||'. Operacion interrumpida.');
rollback;
end;

/
