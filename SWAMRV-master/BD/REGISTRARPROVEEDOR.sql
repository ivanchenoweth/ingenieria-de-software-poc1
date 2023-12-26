--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARPROVEEDOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARPROVEEDOR" (
v_nombre proveedor.nombre%type,
v_idproveedor proveedor.id_proveedor%type,
v_telefono proveedor.telefono%type,
v_descripcion proveedor.descripcion%type,
v_direccion proveedor.direccion%type,
v_email proveedor.email%type,
v_idcomuna proveedor.id_comuna%type)

is
BEGIN
INSERT INTO PROVEEDOR(id_proveedor, nombre, descripcion, telefono, direccion, email, id_comuna) 
VALUES (v_idproveedor, v_nombre, v_descripcion, v_telefono, v_direccion, v_email, v_idcomuna);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Proveedor Ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar el Proveedor '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
