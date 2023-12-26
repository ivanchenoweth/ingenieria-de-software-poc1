--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRAROTSERVICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRAROTSERVICIO" (
v_idot orden_trabajo_servicio.id_ot%type,
v_patente orden_trabajo_servicio.patente%type,
v_idservicio orden_trabajo_servicio.id_servicio%type
)
is
BEGIN
INSERT INTO ORDEN_TRABAJO_SERVICIO(id_ot, patente, id_servicio) 
VALUES (v_idot, v_patente, v_idservicio);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Numero de Patente y Orden ya existente en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la orden '||v_idot||' del vehículo '||v_patente||'. Operacion interrumpida.');
rollback;
end;

/
