--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRAROT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRAROT" (
v_numot orden_trabajo.id_ot%type,
v_patente orden_trabajo.patente%type,
v_rutadmin orden_trabajo.rut_administrador%type,
v_rutmeca orden_trabajo.rut_mecanico%type,
v_fechain orden_trabajo.fecha_inicio%type,
v_fechate orden_trabajo.fecha_termino%type,
v_idtitrab orden_trabajo.tipo_trabajo%type
)
is
BEGIN
INSERT INTO ORDEN_TRABAJO(id_ot, patente, rut_administrador, rut_mecanico, fecha_inicio, fecha_termino, tipo_trabajo) 
VALUES (v_numot, v_patente, v_rutadmin, v_rutmeca, v_fechain, v_fechate, v_idtitrab);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Numero de Patente y Orden ya existente en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la orden '||v_numot||' del vehículo '||v_patente||'. Operacion interrumpida.');
rollback;
end;

/
