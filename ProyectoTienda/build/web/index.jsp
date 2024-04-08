<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesion</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="abs-center">
              <form action="Controlador?accion=Login" class="border p-3 form">
                <div class="form-group">
                    <p class="text-center"><strong>Bienvenidos al Sistema de Login</strong></p>
                </div>
                <div class="form-group">
                  <label>Nombres:</label>
                  <input type="text" name="txtnom" placeholder="Ingrese Nombres" class="form-control">
                </div>
                <div class="form-group">
                  <label>Email:</label>
                  <input type="text" name="txtemail" placeholder="example@gmail.com" class="form-control">
                </div>
                <button class="btn btn-danger btn-block" type="submit" name="accion" value="Ingresar">Login</button>
              </form>
            </div>
        </div> 
        
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>
</html>
