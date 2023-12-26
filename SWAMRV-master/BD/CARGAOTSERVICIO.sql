--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAOTSERVICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAOTSERVICIO" (
v_idot IN orden_trabajo_servicio.id_ot%type,
v_patente IN orden_trabajo_servicio.patente%type,
v_idservicio OUT servicio.id_servicio%type,
v_servnombre OUT servicio.nombre%type,
v_observacion OUT orden_trabajo_servicio.observaciones%type,
v_estado OUT orden_trabajo_servicio.estado%type)
as
BEGIN
SELECT s.id_servicio, s.nombre, ots.observaciones, ots.estado
INTO v_idservicio, v_servnombre, v_observacion, v_estado
FROM orden_trabajo_servicio ots, servicio s
WHERE ots.id_ot = v_idot
AND ots.patente = v_patente
AND s.id_servicio = ots.id_servicio;
commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('DATOS de '||v_idot||' NO ENCONTRADOS');
WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar la orden de servicio '||v_idot||' junto a la patente '||v_patente||'. Operacion interrumpida.');
rollback;
end;

/
