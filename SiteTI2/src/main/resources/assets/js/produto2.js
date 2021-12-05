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
    
	document.getElementById("nome2").innerHTML = objProdutos[1].nome;
	document.getElementById("preco2").innerHTML = "R$ " + objProdutos[1].preco;
	document.getElementById("ficha2").innerHTML = objProdutos[1].fichaTecnica;
	document.getElementById("imagem2").src = objProdutos[1].imagemUrl;
	document.getElementById("imagemHref2").href = objProdutos[1].imagemUrl;
}