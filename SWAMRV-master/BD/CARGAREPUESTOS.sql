--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAREPUESTOS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAREPUESTOS" (
v_nombre IN repuesto.nombre%type,
v_id_repuesto OUT repuesto.id_repuesto%type,
v_cantidad OUT repuesto.cantidad%type,
v_descripcion OUT repuesto.descripcion%type,
v_foto OUT repuesto.foto%type)

as
BEGIN
SELECT id_repuesto, cantidad, descripcion, foto
INTO v_id_repuesto,v_cantidad, v_descripcion, v_foto
FROM REPUESTO WHERE NOMBRE = v_nombre;
commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('REPUESTO no encontrado en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar el repuesto '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
