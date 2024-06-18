<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8" />
        <title>Remover Jogo</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <h1>Remover Jogo</h1>
            <p>Tem certeza que deseja remover o jogo ${jogos.titulo} ?</p>
            <form action="/jogos/delete" method="post">
                <input type="hidden" name="id" value="${jogos.id}" />

                <br />
                <a href="/jogos/list" class="btn btn-secondary">Voltar</a>
                <button type="submit" class="btn btn-success">Remover</button>
            </form>
        </div>
    </body>
</html>