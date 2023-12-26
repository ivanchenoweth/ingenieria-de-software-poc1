--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARVEHICULOCONDUCTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARVEHICULOCONDUCTOR" (
v_rutconductor vehiculo_conductor.rut_conductor%type,
v_patente vehiculo_conductor.patente%type,
v_fecha_asig vehiculo_conductor.fecha_asignacion%type,
v_descripcion vehiculo_conductor.descripcion%type)

is
BEGIN
INSERT INTO VEHICULO_CONDUCTOR(RUT_CONDUCTOR, PATENTE, FECHA_ASIGNACION, DESCRIPCION) 
VALUES (v_rutconductor, v_patente, v_fecha_asig, v_descripcion);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Vehiculo-Conductor ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar al vehiculo-conductor '|| v_rutconductor || ' ' || v_patente ||'. Operacion interrumpida.');
rollback;
end;

/
