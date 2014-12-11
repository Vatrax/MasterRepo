/* -*- mode: Prolog; comment-column: 48 -*- */

/****************************************************************************
 *
 * Copyright (c) 2013 Witold Paluszynski
 *
 * I grant everyone the right to copy this program in whole or part, and
 * to use it for any purpose, provided the source is properly acknowledged.
 *
 * Udzielam kazdemu prawa do kopiowania tego programu w calosci lub czesci,
 * i wykorzystania go w dowolnym celu, pod warunkiem zacytowania zrodla.
 *
 ****************************************************************************/


/*
  This program implements a simple agent strategy for the wumpus world.
  The agent ignores all dangers of the wumpus world.
  The strategy is to go forward along the perimeter,
  turn left when reached the opposing wall,
  but first of all pick up gold if stumbled upon it,
  and exit the game if at home with gold.
  This version registers all steps on a stack, and uses it to reverse
  the actions after having found gold, thus properly returning home.

  Also demonstrates how to keep track of her own position and orientation.
  The agent assumes that the starting point is (1,1) and orientation "east".
*/

% auxiliary initial action generating rule
act(Action, Knowledge) :-

	% To avoid looping on act/2.
	not(gameStarted),
	assert(gameStarted),

	% Creating initial knowledge
	worldSize(X,Y),				%this is given
	assert(myWorldSize(X,Y)),
	assert(myPosition(1, 1, east)),		%this we assume by default
	assert(myTrail([])),
	assert(numberOfMoves(0)),
	assert(haveGold(0)),
	assert(wumpus(1)),
	assert(arrows(1)),

	act(Action, Knowledge).

% standard action generating rules
% this is our agent's algorithm, the rules will be tried in order
act(Action, Knowledge) :- exit_if_home(Action, Knowledge). %if at home with gold
act(Action, Knowledge) :- pick_up_gold(Action, Knowledge). %if just found gold
act(Action, Knowledge) :- go_back_step(Action, Knowledge). %if have gold elsewhere
act(Action, Knowledge) :- resign(Action, Knowledge). %if walking too much go home
act(Action, Knowledge) :- tryToKill(Action, Knowledge). %if stench shoot
act(Action, Knowledge) :- goBackIfDanger(Action, Knowledge). %if back if dangerous
act(Action, Knowledge) :- avoidLooping(Action, Knowledge). %if in loop turn
act(Action, Knowledge) :- turn_if_wall(Action, Knowledge). %if against the wall
act(Action, Knowledge) :- else_move_on(Action, Knowledge). %otherwise

avoidLooping(Action, Knowledge) :-
			%this will fail on a wall
	haveGold(NGolds),
	myWorldSize(Max_X,Max_Y),
	myPosition(X, Y, Orient),
	myTrail(Trail),
	wumpus(WumpusNumber),
	arrows(ArrowsNumber),	
	numberOfMoves(NumberOfMoves),
	member([moveForward,X,Y,Orient], Trail),
	NewNumberOfMoves is NumberOfMoves + 1,
	Action = turnLeft,
	New_Trail = [ [Action,X,Y,Orient] | Trail ],
	Knowledge = [gameStarted,
		     haveGold(NGolds),
	             myWorldSize(Max_X, Max_Y),
		     myPosition(New_X, New_Y, Orient),
		     myTrail(New_Trail),
			 wumpus(WumpusNumber),
			 arrows(ArrowsNumber), 
		     numberOfMoves(NewNumberOfMoves)].

resign(Action, Knowledge) :-
	numberOfMoves(NumberOfMoves), NumberOfMoves > 400,
	haveGold(NGolds),
	myPosition(X, Y, Orient),
	shiftOrient(NewOrient, Orient),
	myWorldSize(Max_X, Max_Y),
	NewNGolds is NGolds + 1,
	myTrail(Trail),
	Action = grab,
	New_Trail = [ [Action,X,Y,Orient] | Trail ],
	Knowledge = [gameStarted,
		     haveGold(NewNGolds),
	         myWorldSize(Max_X, Max_Y),
		  	 myPosition(X, Y, NewOrient),
		     myTrail(New_Trail),
		     numberOfMoves(NumberOfMoves)].

exit_if_home(Action, Knowledge) :-
	haveGold(NGolds), NGolds > 0,
	myPosition(1, 1, Orient),
	Action = exit,				%done game
	Knowledge = [].				%irrelevant but required

goBackIfDanger(Action, Knowledge) :- %STEP 1
	wumpus(WumpusNumber), WumpusNumber > 0,
	stench,
	not(danger),
	haveGold(NGolds),
	wumpus(WumpusNumber),
	myWorldSize(Max_X, Max_Y),
	myTrail(Trail),
	arrows(ArrowsNumber),	
	numberOfMoves(NumberOfMoves),
	NewNumberOfMoves is NumberOfMoves + 1,
	Trail = [ [moveForward,X,Y,Orient] | Trail_Tail ],
	NewTrail = [ [turnRight,X,Y,Orient] | Trail ],
	Action = turnLeft,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(NewTrail),
			 wumpus(WumpusNumber),
			 arrows(ArrowsNumber),
		     numberOfMoves(NewNumberOfMoves), round(1)].

tryToKill(Action, Knowledge) :-
	wumpus(WumpusNumber), WumpusNumber > 0,
	stench,
	not(danger),
	haveGold(NGolds),
	wumpus(WumpusNumber),
	arrows(ArrowsNumber),
	ArrowsNumber > 0,
	NewArrowsNumber is ArrowsNumber - 1,
	myWorldSize(Max_X, Max_Y),
    myPosition(X, Y, Orient),
	myTrail(Trail),
	numberOfMoves(NumberOfMoves),
	NewWumpusNumber is WumpusNumber - 1, %ZLE --------------------------------------------------------
	NewNumberOfMoves is NumberOfMoves + 1,
	Action = shoot,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(Trail),
			 wumpus(NewWumpusNumber),
			 arrows(NewArrowsNumber),
		     numberOfMoves(NewNumberOfMoves)].


goBackIfDanger(Action, Knowledge) :- %STEP 1
	wumpus(WumpusNumber),
	breeze,
	not(danger),
	haveGold(NGolds),
	wumpus(WumpusNumber),
	arrows(ArrowsNumber),	
	myWorldSize(Max_X, Max_Y),
	myTrail(Trail),
	numberOfMoves(NumberOfMoves),
	NewNumberOfMoves is NumberOfMoves + 1,
	Trail = [ [moveForward,X,Y,Orient] | Trail_Tail ],
	NewTrail = [ [turnRight,X,Y,Orient] | Trail ],
	Action = turnLeft,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(NewTrail),
			 wumpus(WumpusNumber),
			 arrows(ArrowsNumber),
		     numberOfMoves(NewNumberOfMoves), round(1)].

goBackIfDanger(Action, Knowledge) :- %STEP 2 Turn round
	round(NRound), NRound > 0,
	haveGold(NGolds),
	wumpus(WumpusNumber),
	breeze,
	myWorldSize(Max_X, Max_Y),
	wumpus(WumpusNumber),
	arrows(ArrowsNumber),
	numberOfMoves(NumberOfMoves),
	myTrail([ [OldAct,X,Y,Orient] | Trail_Tail ]),
	NewNumberOfMoves is NumberOfMoves + 1,
	%% if our previous action was a turn, we must reverse it now
	((OldAct=turnLeft,Action=turnRight);(OldAct=turnRight,Action=turnLeft)),
	NewNRound is NRound -1,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(Trail_Tail),
			 arrows(ArrowsNumber),
			 wumpus(WumpusNumber),
		     numberOfMoves(NewNumberOfMoves), round(NewNRound), danger].

goBackIfDanger(Action, Knowledge) :- %STEP 2 Turn round
	round(NRound), NRound > 0,
	haveGold(NGolds),
	wumpus(WumpusNumber), WumpusNumber > 0,
	stench, 
	myWorldSize(Max_X, Max_Y),
	wumpus(WumpusNumber),
	numberOfMoves(NumberOfMoves),
	arrows(ArrowsNumber),
	myTrail([ [OldAct,X,Y,Orient] | Trail_Tail ]),
	NewNumberOfMoves is NumberOfMoves + 1,
	%% if our previous action was a turn, we must reverse it now
	((OldAct=turnLeft,Action=turnRight);(OldAct=turnRight,Action=turnLeft)),
	NewNRound is NRound -1,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     arrows(ArrowsNumber), 
		     myPosition(X, Y, Orient),
		     myTrail(Trail_Tail),
			 wumpus(WumpusNumber),
		     numberOfMoves(NewNumberOfMoves), round(NewNRound), danger].

goBackIfDanger(Action, Knowledge) :- % STEP 3
	danger,
	haveGold(NGolds),
	myWorldSize(Max_X, Max_Y),
	myTrail(Trail),
	wumpus(WumpusNumber),
	myPosition(X, Y, Orient),
	numberOfMoves(NumberOfMoves),
	arrows(ArrowsNumber),	
	NewNumberOfMoves is NumberOfMoves + 1,
	Action = moveForward,
	Trail_Tail = [ [Action,X,Y,Orient] | Trail ],
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     arrows(ArrowsNumber), 
		     myPosition(X, Y, Orient),
			 wumpus(WumpusNumber),
		     myTrail(Trail_Tail),
		     numberOfMoves(NewNumberOfMoves), noDanger].

goBackIfDanger(Action, Knowledge) :- % STEP 4
	noDanger,
	haveGold(NGolds),
	wumpus(WumpusNumber),
	myWorldSize(Max_X, Max_Y),
	myTrail(Trail),
	arrows(ArrowsNumber),
    myPosition(X, Y, Orient),
    shiftOrient(Orient, NewOrient),
	numberOfMoves(NumberOfMoves),
	NewNumberOfMoves is NumberOfMoves + 1,
	Action = turnRight,
	NewTrail = [ [turnLeft,X,Y,NewOrient] | Trail ],
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     arrows(ArrowsNumber), 
		     myPosition(X, Y, NewOrient),
		     myTrail(NewTrail),
			 wumpus(WumpusNumber),
		     numberOfMoves(NewNumberOfMoves)].

go_back_step(Action, Knowledge) :-
	%%% assuming we have just found gold:
	%%% 1. our last action must have been grab
	%%% 2. our previuos action must have been moveForward
	%%% 3. so we are initiating a turnback and then return:
	%%%    (a) pop grab from the stack
	%%%    (b) replace it by an artificial turnRight we have never
	%%%        executed, but we will be reversing by turning left
	%%%    (c) execute a turnRight now which together will turn us back
	%%% 4. after that we are facing back and can execute actions in reverse
	%%% 5. because of grab we can be sure this rule is executed exactly once
	haveGold(NGolds), NGolds > 0,
	myWorldSize(Max_X, Max_Y),
	myTrail(Trail),
	numberOfMoves(NumberOfMoves),
	Trail = [ [grab,X,Y,Orient] | Trail_Tail ],
	New_Trail = [ [turnLeft,X,Y,Orient] | Trail_Tail ], %Orient is misleading here
	Action = turnRight,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(New_Trail),
		     numberOfMoves(NumberOfMoves)].

go_back_step(Action, Knowledge) :-
	haveGold(NGolds), NGolds > 0,
	myWorldSize(Max_X, Max_Y),
	numberOfMoves(NumberOfMoves),
	myTrail([ [Action,X,Y,Orient] | Trail_Tail ]),
	Action = moveForward,
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(Trail_Tail),
		     numberOfMoves(NumberOfMoves)].

%%% backtracking a step can be moving or can be turning
go_back_step(Action, Knowledge) :- go_back_turn(Action, Knowledge).

go_back_turn(Action, Knowledge) :-
	haveGold(NGolds),
	NGolds > 0,
	myWorldSize(Max_X, Max_Y),
	numberOfMoves(NumberOfMoves),
	myTrail([ [OldAct,X,Y,Orient] | Trail_Tail ]),
	NewNumberOfMoves is NumberOfMoves + 1,
	%% if our previous action was a turn, we must reverse it now
	((OldAct=turnLeft,Action=turnRight);(OldAct=turnRight,Action=turnLeft)),
	Knowledge = [gameStarted,
	             haveGold(NGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),
		     myTrail(Trail_Tail),
		     numberOfMoves(NewNumberOfMoves)].

pick_up_gold(Action, Knowledge) :-
	glitter,
	Action = grab,			    %this is easy, we are sitting on it
	haveGold(NGolds),		    %we must know how many golds we have
	NewNGolds is NGolds + 1,
	myWorldSize(Max_X, Max_Y),
	myPosition(X, Y, Orient),
	myTrail(Trail),
	numberOfMoves(NumberOfMoves),
	New_Trail = [ [Action,X,Y,Orient] | Trail ], %important to remember grab
	Knowledge = [gameStarted,
	             haveGold(NewNGolds),
		     myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, Orient),	%the position stays the same
		     myTrail(New_Trail),
		     numberOfMoves(NumberOfMoves)].

turn_if_wall(Action, Knowledge) :-
	myPosition(X, Y, Orient),
	myWorldSize(Max_X,Max_Y),
	wumpus(WumpusNumber),
	againstWall(X, Y, Orient, Max_X, Max_Y),
	Action = turnLeft,			%always successful
	shiftOrient(Orient, NewOrient),		%always successful
	haveGold(NGolds),
	arrows(ArrowsNumber),	
	numberOfMoves(NumberOfMoves),
	NewNumberOfMoves is NumberOfMoves + 1,
	myTrail(Trail),
	New_Trail = [ [Action,X,Y,Orient] | Trail ],
	Knowledge = [gameStarted,
		     haveGold(NGolds),
	             myWorldSize(Max_X, Max_Y),
		     myPosition(X, Y, NewOrient),
		     myTrail(New_Trail),
		     arrows(ArrowsNumber), 
		     wumpus(WumpusNumber),
		     numberOfMoves(NewNumberOfMoves)].

againstWall(X, Y, Orient, Max_X, Max_Y) :- X = Max_X, Y = 1,     Orient = east.
againstWall(X, Y, Orient, Max_X, Max_Y) :- X = Max_X, Y = Max_Y, Orient = north.
againstWall(X, Y, Orient, Max_X, Max_Y) :- X = 1,     Y = Max_Y, Orient = west.
againstWall(X, Y, Orient, Max_X, Max_Y) :- X = 1,     Y = 1,     Orient = south.

againstWall(X, Y, Orient, Max_X, Max_Y) :- X = Max_X, Orient = east.
againstWall(X, Y, Orient, Max_X, Max_Y) :- X = 1,	  Orient = west.
againstWall(X, Y, Orient, Max_X, Max_Y) :- Y = Max_Y,     Orient = north.
againstWall(X, Y, Orient, Max_X, Max_Y) :- Y = 1, Orient = south.

shiftOrient(east, north).
shiftOrient(north, west).
shiftOrient(west, south).
shiftOrient(south, east).

else_move_on(Action, Knowledge) :-
	Action = moveForward,			%this will fail on a wall
	haveGold(NGolds),
	myWorldSize(Max_X,Max_Y),
	myPosition(X, Y, Orient),
	forwardStep(X, Y, Orient, New_X, New_Y),
	myTrail(Trail),
	wumpus(WumpusNumber),
	arrows(ArrowsNumber),	
	numberOfMoves(NumberOfMoves),
	NewNumberOfMoves is NumberOfMoves + 1,
	New_Trail = [ [Action,X,Y,Orient] | Trail ],
	Knowledge = [gameStarted,
		     haveGold(NGolds),
	             myWorldSize(Max_X, Max_Y),
		     myPosition(New_X, New_Y, Orient),
		     myTrail(New_Trail),
			 wumpus(WumpusNumber),
			 arrows(ArrowsNumber), 
		     numberOfMoves(NewNumberOfMoves)].

forwardStep(X, Y, east,  New_X, Y) :- New_X is (X+1).
forwardStep(X, Y, south, X, New_Y) :- New_Y is (Y-1).
forwardStep(X, Y, west,  New_X, Y) :- New_X is (X-1).
forwardStep(X, Y, north, X, New_Y) :- New_Y is (Y+1).