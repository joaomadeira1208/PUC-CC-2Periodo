module Exercicio0202;
    //Data
    integer counter = 7;
    real x = 0.75; // parte fracionada
    integer y = 50; // parte inteira
    reg[7:0] b2 = 0;
    reg[7:0] b = 0;

    initial 
        begin: main
            $display("Exercise 2 - Guide 2");
            $display("%f (10)", x);
            b2 = y;
            while(x > 0)
                begin
                    if(x*2 >= 1)
                        begin
                            b[counter] = 1;
                            x = x*2 - 1;
                        end
                    else
                        begin
                            b[counter] = 0;
                            x = x*2.0;
                        end
                    counter = counter - 1;
                end
            $display("%b.%b (2)", b2, b);
        end
endmodule  
