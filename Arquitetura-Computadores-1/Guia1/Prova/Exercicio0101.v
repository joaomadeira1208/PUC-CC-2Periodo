
module Exercicio0101;
    integer x = 26;
    reg[7:0] b = 0;

    initial 
        begin: main
            $display("Exercicio 1 - Guia 1");
            $display("x = %d", x);
            b = x;
            $display("b = %8b", b);
        end
    
endmodule
            
    