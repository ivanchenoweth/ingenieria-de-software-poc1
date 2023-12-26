--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRAROTREP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRAROTREP" (
v_idot orden_trabajo_repuesto.id_ot%type,
v_patente orden_trabajo_repuesto.patente%type,
v_idrepuesto orden_trabajo_repuesto.id_repuesto%type,
v_rutencargado orden_trabajo_repuesto.rut_encargado%type,
v_observaciones orden_trabajo_repuesto.observaciones%type,
v_cantidad orden_trabajo_repuesto.cantidad%type
)
is
cantidad number := 0;
BEGIN
INSERT INTO ORDEN_TRABAJO_REPUESTO(id_ot, patente, id_repuesto, rut_encargado, observaciones, cantidad) 
VALUES (v_idot, v_patente, v_idrepuesto, v_rutencargado, v_observaciones, v_cantidad);

SELECT CANTIDAD into cantidad
FROM REPUESTO WHERE id_repuesto = v_idrepuesto;

UPDATE REPUESTO SET CANTIDAD = cantidad - v_cantidad
where id_repuesto = v_idrepuesto;

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Numero de Patente, Orden de trabajo y Repuesto ya existente en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la orden '||v_idot||' del vehículo '||v_patente||' con el repuesto '||v_idrepuesto||'. Operacion interrumpida.');
rollback;
end;

/
