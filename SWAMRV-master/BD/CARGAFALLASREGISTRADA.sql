--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAFALLASREGISTRADA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAFALLASREGISTRADA" (
v_idot IN orden_trabajo_falla.id_ot%type,
v_patente IN orden_trabajo_falla.patente%type,
v_nombre IN falla.nombre%type,
v_observaciones OUT orden_trabajo_falla.observaciones%type,
v_idfalla OUT falla.id_falla%type,
v_foto OUT falla.foto%type )

as
BEGIN
SELECT otf.observaciones, otf.id_falla, f.foto
INTO v_observaciones, v_idfalla, v_foto
FROM ORDEN_TRABAJO_FALLA otf, FALLA f
WHERE otf.ID_OT = v_idot
and otf.PATENTE = v_patente
and f.nombre = v_nombre
and f.id_falla = otf.id_falla;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('FALLA no encontrada en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar la falla. Operacion interrumpida.');
rollback;
end;

/
