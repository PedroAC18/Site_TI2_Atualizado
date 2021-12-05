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
	document.getElementById("imagem1").src = objProdutos[0].imagemUrl;
	document.getElementById("imagem1Alt").src = objProdutos[0].imagemUrl;
	
	
	document.getElementById("nome2").innerHTML = objProdutos[1].nome;
	document.getElementById("preco2").innerHTML = "R$ " + objProdutos[1].preco;
	document.getElementById("imagem2").src = objProdutos[1].imagemUrl;
	document.getElementById("imagem2Alt").src = objProdutos[1].imagemUrl;
	
	document.getElementById("nome3").innerHTML = objProdutos[2].nome;
	document.getElementById("preco3").innerHTML = "R$ " + objProdutos[2].preco;
	document.getElementById("imagem3").src = objProdutos[2].imagemUrl;
	document.getElementById("imagem3Alt").src = objProdutos[2].imagemUrl;
	
}