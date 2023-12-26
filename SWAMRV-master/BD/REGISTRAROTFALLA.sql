--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRAROTFALLA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRAROTFALLA" (
v_idot orden_trabajo_falla.id_ot%type,
v_patente orden_trabajo_falla.patente%type,
v_idfalla orden_trabajo_falla.id_falla%type,
v_observaciones orden_trabajo_falla.observaciones%type
)
is
cantidad number := 0;
BEGIN
INSERT INTO ORDEN_TRABAJO_FALLA(id_ot, patente, id_falla, observaciones) 
VALUES (v_idot, v_patente, v_idfalla, v_observaciones);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Numero de Patente, Orden de trabajo y Falla ya existente en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la orden '||v_idot||' del vehículo '||v_patente||' con la falla '||v_idfalla||'. Operacion interrumpida.');
rollback;
end;

/
