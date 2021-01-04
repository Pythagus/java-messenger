<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Publish GET</title>
    <style type="text/css">
      body, html {
        padding: 0 !important ;
        margin: 0 !important ;
        height: 100vh ;
        width: 100vw ;
      }
      body {
        background: #f3f4f5 ;
        font-family: 'Verdana', sans-serif ;
      }
    </style>
  </head>
  <body>
    <form method="POST" action="${pageContext.request.contextPath}/publish">
      <input type="text" name="identifier" placeholder="Identifier">
      <select name="status">
        <option value="DISCONNECTED">Déconnecté</option>
        <option value="IDLE">Absent</option>
        <option value="CONNECTED">Connecté</option>
      </select>
      <button type="submit">Envoyer</button>
    </form>
  </body>
</html>
