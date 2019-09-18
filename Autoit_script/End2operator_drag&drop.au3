;;;;;; Start operator ;;;;;;;;;;;;;;;
;MouseMove(100,320,70)
;Sleep( 1000 )
;MouseClickDrag("LEFT",100,320,450,300,90)
;MouseMove(450,300, 99)
;Sleep( 500 )
;MouseClick("LEFT", 450,300, 3)
;Send("Start",1)
;MouseClick("LEFT", 450,300, 1)
;MouseClick("RIGHT",450,300)


;;;;;;;;;;;;; InvokeBS ;;;;;;;;;;;;;;;;
;MouseMove(100,520,70)
;Sleep (1000)
;MouseClickDrag("LEFT",100,520,650,300,40)
;MouseMove(650,300, 99)
;MouseClick("LEFT", 650,300, 3)
;Send("InvokeBS", 1)
;MouseClick("LEFT", 650,300, 1)
;MouseClick("RIGHT",650,300)

;;;;;;;;;;;;; MOUSEUP & DM Exclusive drag & drop;;;;;;;;;;;;;;;;;;
;MouseMove(100,525,70)
;MouseClick("LEFT", 100,525,1)
;MouseWheel("DOWN", 1)
;Sleep( 1000 )
;MouseMove(100,533,70)
;MouseClickDrag("LEFT",100,533,850,300,40)
;MouseMove(850,300)
;MouseClick("LEFT", 850,300, 3)
;Send("DMExclusive1")
;MouseClick("LEFT", 850,300,1)
;MouseClick("RIGHT", 850,300)


;;;;;;;;;;;;; MOUSEUP & JOIN drag&drop ;;;;;;;;;;;;;;
;MouseMove(100,533,70)
;MouseClick("LEFT", 100,533,1,70)
;MouseWheel("DOWN", 3)
;Sleep( 1000 )
;MouseMove(100,435,70)
;MouseClickDrag("LEFT",100,435,1050,300,40)
;MouseMove(1050, 300)
;MouseClick("LEFT", 1050, 300, 3)
;Send("Join1",1)
;MouseClick("LEFT",1050, 300,1)
;MouseClick("RIGHT", 1050,300)


;;;;;;;; Mouseup & DM Inclusive drag&drop ;;;;;;;;;;;;;;;;
;MouseMove(100, 335, 70)
;Sleep ( 1000 )
;MouseClickDrag("LEFT", 100,335, 1050, 450, 90)
;MouseMove(1050, 450)
;MouseClick("LEFT", 1050,450, 3)
;Send("DMInclusive1",1)
;MouseClick("LEFT", 1050,450)
;MouseClick("RIGHT", 1050,450)

;;;;;;;;;;;;;;;;; mouseup & join drag&drop ;;;;;;;;;;;;;;;;
;MouseMove(100,440,70)
;Sleep( 1000 )
;MouseClickDrag("LEFT",100,440,850,450,40)
;MouseMove(850,450)
;MouseClick("LEFT", 850,450,3)
;Send("Join2",1)
;MouseClick("LEFT", 850,450,1)
;MouseClick("RIGHT", 850,450)

;;;;;;;;;;;;;;; mouseup & Endoperator drag&drop ;;;;;;;;;;;;;;
;MouseMove(100,440,10)
;MouseClick("LEFT", 100,440,10)
;Sleep( 1000 )
;MouseWheel("UP",4)
;Sleep( 1000 )
;MouseMove(100,442,70)
;MouseClickDrag("LEFT", 100,442,650,450,40)
;MouseMove(650,450)
;MouseClick("LEFT",650,450,3)
;Send("End1",1)
;MouseClick("LEFT", 650,450,1)
;MouseClick("RIGHT", 650,450)

;;;;;;;;;;;;;;;;;; mouseup & End2 operator drag&drop ;;;;;;;;;;;
MouseMove(100, 442, 70)
MouseClickDrag("LEFT", 100, 442, 650, 550, 20)
MouseMove(650,550)
MouseClick("LEFT", 650,550,3)
Send("End2",1)
MouseClick("LEFT",650,550,1)
MouseClick("RIGHT", 650,550)

;;;;;;;;;; start & InvokeBS normal connector ;;;;;;;;;;
;MouseClick("LEFT", 650,550)
;Sleep( 2000 )
;MouseMove(480,300,100)
;Sleep ( 1000 )
;MouseClickDrag("LEFT",480,300, 620, 300, 100)

;;;;;;;;;; Invokebs & DM Exclusive normal connector ;;;;
;MouseClickDrag("LEFT", 680,300, 820,300, 100)

;;;;;;;;;; DM Exclusive & Join normal connector ;;;;
;MouseClickDrag("LEFT", 880,300, 1020,300, 100)
;MouseClickDrag("LEFT", 880,300, 1020,300, 100)

;;;;;;;;;; Join & DMInclusive normal connector ;;;;
;MouseClickDrag("LEFT", 1050,330, 1050,420, 100)

;;;;;;;;;; DMInclusive & Join normal connector ;;;;
;MouseClickDrag("LEFT", 1018,450, 880,450, 100)
;MouseClickDrag("LEFT", 1018,450, 880,450, 100)

;;;;;;;;;;  Join & End1 smart connector ;;;;
;MouseMove(100, 442, 100)
;MouseClick("LEFT", 100, 442,1)
;MouseWheel("DOWN", 6)
;Sleep(1000)
;MouseMove(100,590,1);
;Sleep( 1000 )
;MouseClickDrag("LEFT",100, 590, 875, 450, 100)
;MouseClickDrag("LEFT", 923, 450, 680,450,100)
;MouseClick("RIGHT", 715,450,1)


;;;;;;;;;;  Join & End2 normal connector ;;;;
;MouseMove(850,450, 100)
;MouseClick(850,450,1)
;Sleep( 1000 )
;MouseMove(818,450)
;Sleep( 500 )
;MouseClickDrag("LEFT", 818,450, 680,550, 99)





 ;MouseClickDrag("LEFT",90,470,650,300,40)
 ;MouseClick("RIGHT",650,300)
 ;sleep(5000)

;#include <Join2_drag&drop.au3>

 ;Local $mouseclick_x = 100
 ;Local $mouseclick_y = 440

;MouseClick("LEFT",$mouseclick_x,$mouseclick_y,1)
;MouseWheel("UP", 4))
;mouseclick_draganddrop()
;Exit


;Func mouseclick_draganddrop
 ;MouseClickDrag("LEFT",100,440,650,400,70)
 ;MouseClickDrag("LEFT",680,300,820,300,50)
 ;EndFunc









; MouseClickDrag("LEFT",436,240,470,240)
 ;MouseClickDrag("LEFT",185,350,600,240)