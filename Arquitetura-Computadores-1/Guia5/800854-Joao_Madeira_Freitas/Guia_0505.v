// Guia_0505
// João Madeira Carneiro Braga de Freitas - 800854

module nandModule(input a,input b,output s);
	nand nandA(s,a,b);
endmodule 

module norModule(input a,input b,output s);
	nor norA(s,a,b);
endmodule 

module orComNand(input a,input b,output s);
	wire not_a;
	wire not_b;
	nandModule moduloNotA1(a,a,not_a);
	nandModule moduloNotA2(b,b,not_b);
	nandModule moduloNand(not_a,not_b,s);
endmodule 

module andComNand(input a,input b,output s);
	wire nandA;
	nandModule moduloNand(a,b,nandA);
	nandModule moduloNot(nandA,nandA,s);
endmodule 

module orComNor(input a,input b, output s);
	wire norA;
	norModule moduleNor(a,b,norA);
	norModule moduleNot(norA,norA,s);
endmodule 	

module andComNor(input a,input b,output s);
	wire not_a;
	wire not_b;
	norModule moduleNot1(a,a,not_a);
	norModule moduleNot2(b,b,not_b);
	norModule moduleResposta(not_a,not_b,s);
endmodule 

module notComNand(input a,output s);
	nandModule moduleNot(a,a,s);
endmodule 

module notComNor(input a,output s);
	norModule moduleNot(a,a,s);
endmodule 

module xorComNand(input a,input b,output s);
	wire notA,notB,andA,andB;
	notComNand not_A1(a,notA);
	notComNand not_A2(b,notB);
	andComNand and_A1(notA,b,andA);
	andComNand and_A2(a,notB,andB);
	orComNand orSolution(andA,andB,s);
endmodule 

module xnorComNor(input a,input b,output s);
	wire notA,notB,andA,andB,orA;
	notComNor notA1(a,notA);
	notComNor notA2(b,notB);
	andComNor andA1(notA,b,andA);
	andComNor andA2(a,notB,andB);
	orComNor orA1(andA,andB,orA);
	notComNor notSolution(orA,s);
endmodule 

module testeXnorComNor;

//Definição de dados:
	reg x;
	reg y;
	wire xnorNor;
    xnorComNor solution(x,y,xnorNor);
//Fim da definição de dados.


initial
begin : teste_guia_0505
	$display("Guia_0505 - x,y,z");
	$display("Teste de modulo");
	$display("a b s");
	$monitor("%b %b %b",x,y,xnorNor);
	x = 1'b0; y = 1'b0;
#1 x = 1'b0; y = 1'b1;
#1 x = 1'b1; y = 1'b0;
#1 x = 1'b1; y = 1'b1;
end
endmodule