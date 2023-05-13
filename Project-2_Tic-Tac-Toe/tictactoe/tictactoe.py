"""
Tic Tac Toe Player
"""

import math
import copy
import sys

X = "X"
O = "O"
EMPTY = None


class Node():
    def __init__(self,t,point):
        self.t = t
        self.point = point
        


def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    numberOfX = 0
    numberOfO = 0
    numberOFEmpty = 0

    for row in board:
        for element in row:
            if(element == X):
                numberOfX=numberOfX+1
            elif(element == O):
                numberOfO=numberOfO+1
            else:
                numberOFEmpty=numberOFEmpty+1
    
    if(numberOFEmpty == 9):
        return X
    elif(numberOfX < numberOfO):
        return X
    elif(numberOfO < numberOfX):
        return O
    
    return X
    

    raise NotImplementedError


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    rows = len(board)

    columns = len(board[0])

    possible_actions_set = set()

    for i in range(rows):
        for j in range(columns):
            if(board[i][j] == EMPTY):
                t = (i,j)
                possible_actions_set.add(t)
    
    return possible_actions_set


    raise NotImplementedError


def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """

    ## if action is not a valid move then raise an exception

    ## out of board control
    
    if(action[0] < 0 or action[0] > 2) :
        raise Exception("invalid move")
    if(action[1] < 0 or action[1] > 2):
        raise Exception("invalid move")
    
    
    ## the control of movement to already filled place


    i = action[0]
    j = action[1]

    if(board[i][j] != EMPTY):
        raise Exception("invalid move")
    
    ## firstly, deepcopy should be made not to lose infromation 

    copy_matrix = copy.deepcopy(board)

    whose_turn = player(board)

    if(whose_turn == X):
        copy_matrix[i][j] = X
    elif(whose_turn == O):
        copy_matrix[i][j] = O

    ## and return the changed copy matrix without changing the original board

    return copy_matrix


    raise NotImplementedError


def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    rows = len(board)

    columns = len(board[0])

    ## makes control of horizontally winning

    for i in range(rows):
        numberX = 0
        numberO = 0
        for j in range(columns):
            if(board[i][j] == X):
                numberX = numberX+1
            elif(board[i][j] == O):
                numberO = numberO+1
        if(numberX == 3):
            return X
        elif(numberO == 3):
            return O
        
    ## makes control of vertically winning
    
    for j in range(columns):
        numberX = 0
        numberO = 0
        for i in range(rows):
            if(board[i][j] == X):
                numberX = numberX+1
            elif(board[i][j] == O):
                numberO = numberO+1
        if(numberX == 3):
            return X
        elif(numberO == 3):
            return O
        
    ## makes control of diagonally winning

    if(board[0][0] == X and board[1][1] == X and board[2][2] == X  ):
        return X
    
    if(board[0][2] == X and board[1][1] == X and board[2][0] == X  ):
        return X
    
    if(board[0][0] == O and board[1][1] == O and board[2][2] == O  ):
        return O
    
    if(board[0][2] == O and board[1][1] == O and board[2][0] == O  ):
        return O
    
    ## if the game has no winner return none

    return None


    raise NotImplementedError


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """
    ## if someone wins the game, game is over

    temp_result = winner(board)

    if(temp_result == X or temp_result == O):
        return True
    
    ## if none wins game and board is full, game is over 
    
    numberof_empty_cells = 0

    for row in board:
        for element in row:
            if(element == EMPTY):
                numberof_empty_cells = numberof_empty_cells+1
    
    if(numberof_empty_cells == 0):
        return True
    
    ## otherwise game is not finished return false

    return False


    raise NotImplementedError


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """
    temp_result = winner(board)

    ## Note that this function will be called if(terminal(board)) is True
    ## so, at most three condition will occur

    if(temp_result == X):
        return 1
    elif(temp_result == O):
        return -1
    else:
        return 0


    raise NotImplementedError


def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    """

    ## if the board is a terminal board return None

    if(terminal(board)):
        return None
    
    ## get the current player
    current_player = player(board)

    ## initialize the best_score and best_action variables
    if current_player == X:
        best_score = float("-inf")
    else:
        best_score = float("inf")
    best_action = None

    ## iterate over all possible actions
    for action in actions(board):
        ## get the result of making the action on the board
        new_board = result(board, action)

        ## calculate the score for the new board using the minimax algorithm
        score = minimax_score(new_board)

        ## update the best_score and best_action variables based on the current player
        if current_player == X:
            if score > best_score:
                best_score = score
                best_action = action
        else:
            if score < best_score:
                best_score = score
                best_action = action
    
    ## return the best action
    return best_action
        

    

    
    ##raise NotImplementedError

def minimax_score(board):
    """
    Returns the score of the board based on the minimax algorithm.
    """
    ## if the board is a terminal board, return the utility value
    if terminal(board):
        return utility(board)

    ## get the current player
    current_player = player(board)

    ## initialize the score variable based on the current player
    if current_player == X:
        best_score = float("-inf")
    else:
        best_score = float("inf")

    ## iterate over all possible actions
    for action in actions(board):
        ## get the result of making the action on the board
        new_board = result(board, action)

        ## calculate the score for the new board using the minimax_score function
        score = minimax_score(new_board)

        ## update the best_score variable based on the current player
        if current_player == X:
            best_score = max(best_score, score)
        else:
            best_score = min(best_score, score)

    ## return the best_score
    return best_score







    
    
    
    
    
    
