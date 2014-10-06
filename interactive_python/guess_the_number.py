import math
import random
import simplegui

secret_number = 0
guess_left = 7
range = 100

# helper function to start and restart the game
def new_game():
    '''
    Start a new game.
    '''
    # initialize global variables used in your code here
    global secret_number
    global range
    global guess_left
    
    if range == 1000:
        gues_left = 10
    else:
        guess_left = 7
    
    print 'New game. Range is 0 to ' + str(range) + '\n'
    print 'You have ' + str(guess_left) + ' guesses left.\n'
    secret_number = random.randrange(0, range)

# define event handlers for control panel
def range100():
    '''
    Set the range to be 100.
    '''
    # button that changes the range to [0,100) and starts a new game 
    global range
    global guess_left
    range = 100
    
    new_game()
    # remove this when you add your code    
    pass

def range1000():
    '''
    Set the range to be 1000.
    '''
    # button that changes the range to [0,1000) and starts a new game     
    global range
    global guess_left
    range = 1000
    
    new_game()
    
def input_guess(guess):
    '''
    Process the user input.
    '''
    # main game logic goes here
    global secret_number
    global guess_left
   
    guess_number = int(guess)
    print "Guess was " + guess
    if guess_number < secret_number:
        print "Higher\n"
    elif guess_number > secret_number:
        print "Lower\n"
    else:
        print "Correct\n"
        new_game()

    guess_left -= 1    
    if guess_left == 0:
        print "You ran out of guesses. Number was " + str(secret_number) + "\n"
        new_game()
        

# create frame
frame = simplegui.create_frame('Guess the Number', 200, 200)

# register event handlers for control elements and start frame
frame.add_button("Range is [0, 100)", range100, 200)
frame.add_button("Range is [0, 1000)", range1000, 200)
frame.add_input("Enter a guess", input_guess, 200)

# call new_game 
new_game()
