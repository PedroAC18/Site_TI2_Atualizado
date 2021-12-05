	// Main
$(window).on("load" , function () {
    // Carregar table de bebidas
    requestProduto();
});

// Fazer requisição das bebidas no servidor
function requestProduto() {
    // Variável de requisição
    let xhr = new XMLHttpRequest();
    let actionSrc = `/all/produto/`;
    let method = 'GET';

    xhr.addEventListener( 'load', function ( event ) {
        loadProduto(event.target.responseText);
    } );

    xhr.open(method, actionSrc);

    xhr.send();
}
	
// Carregar as bebidas na tabela
function loadProduto( produtos ) {
    let objProdutos = JSON.parse(produtos);
    
    // Tabela de bebidas
    
	document.getElementById("nome3").innerHTML = objProdutos[2].nome;
	document.getElementById("preco3").innerHTML = "R$ " + objProdutos[2].preco;
	document.getElementById("ficha3").innerHTML = objProdutos[2].fichaTecnica;
	document.getElementById("imagem3").src = objProdutos[2].imagemUrl;
	document.getElementById("imagemHref3").href = objProdutos[2].imagemUrl;
}