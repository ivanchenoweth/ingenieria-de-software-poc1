--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARVEHICULO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARVEHICULO" (
v_patente vehiculo.patente%type,
v_chasis vehiculo.chasis%type,
v_ano vehiculo.ano%type,
v_color vehiculo.color%type,
v_marca vehiculo.marca%type,
v_modelo vehiculo.modelo%type,
v_fechain vehiculo.fecha_ingreso%type,
v_foto vehiculo.foto%type)

is
BEGIN
UPDATE VEHICULO
SET chasis = v_chasis,
ano = v_ano,
color = v_color,
marca = v_marca,
modelo = v_modelo,
fecha_ingreso = v_fechain,
foto = v_foto
WHERE patente = v_patente;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar el Vehiculo '||v_patente||'. Operacion interrumpida.');
rollback;
end;

/
