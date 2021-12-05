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
    
	document.getElementById("nome1").innerHTML = objProdutos[0].nome;
	document.getElementById("preco1").innerHTML = "R$ " + objProdutos[0].preco;
	document.getElementById("ficha1").innerHTML = objProdutos[0].fichaTecnica;
	document.getElementById("imagem1").src = objProdutos[0].imagemUrl;
	document.getElementById("imagemHref1").href = objProdutos[0].imagemUrl;
}