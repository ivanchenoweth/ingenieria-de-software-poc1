--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure BUSCARADMIN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."BUSCARADMIN" (
v_rutadmin IN administrador.rut_administrador%type,
v_contrasena IN administrador.contrasena%type,
v_nombre OUT administrador.nombre%type,
v_apellidopaterno OUT administrador.apellido_paterno%type,
v_apellidomaterno OUT administrador.apellido_materno%type
)

is
BEGIN

SELECT nombre, apellido_paterno, apellido_materno
INTO v_nombre, v_apellidopaterno, v_apellidomaterno
FROM ADMINISTRADOR
WHERE rut_administrador = v_rutadmin
and contrasena = v_contrasena;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se ha encontrado el Encargado de Bodega');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al intentar cargar al usuario' || v_rutadmin || ' . Operacion interrumpida.');
rollback;
end;

/
