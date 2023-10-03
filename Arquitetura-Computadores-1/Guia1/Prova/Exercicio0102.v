

module Exercicio0102;
    integer x = 0;
    reg[7:0] b = 8'b00011010;

    initial 
        begin: main
            $display("Exercicio 2 - Guia 1");
            $display("b = %8b", b);
            $display("x = %d (before converion)", x);
            x = b;
            $display("x = %d (after conversion)", x);
        end
    
endmodule
