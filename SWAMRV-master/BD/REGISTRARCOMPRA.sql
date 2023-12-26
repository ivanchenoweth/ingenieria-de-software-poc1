--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARCOMPRA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARCOMPRA" (
v_nrofactura compra.nro_factura%type,
v_fecha compra.fecha_compra%type,
v_idproveedor compra.id_proveedor%type,
v_rutadmin compra.rut_administrador%type,
v_detalle compra.detalle%type
)
is
BEGIN
INSERT INTO COMPRA(nro_factura, fecha_compra, id_proveedor, rut_administrador, detalle) 
VALUES (v_nrofactura, v_fecha, v_idproveedor, v_rutadmin, v_detalle);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Numero de de de Compra ya existente en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la compra '||v_nrofactura||'. Operacion interrumpida.');
rollback;
end;

/
