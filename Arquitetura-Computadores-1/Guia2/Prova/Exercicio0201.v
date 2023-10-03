module Exercicio0201;
    real x = 0;
    real power2 = 1.0;
    integer counter = 4;
    reg[4:0] b = 8'b01101;

    initial
        begin: main
            $display("Exercise 1 - Guide 2");
            $display("0.%b (2)", b);
            while(counter >= 0)
                begin
                    power2 = power2/2;
                    if(b[counter] == 1)
                        begin
                            x = x + power2;
                        end
                    counter = counter - 1;
                end
            $display("%f (10)", x);
            
        end
endmodule