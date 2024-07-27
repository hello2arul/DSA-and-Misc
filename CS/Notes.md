### What Happens Internally When You Press a Key
1. Key Press Detection: The keyboard sends a signal to the CPU via the keyboard controller.
2. Interrupt Handling: An interrupt is generated, prompting the CPU to halt current operations and process the input.
3. Input Processing: The CPU reads the key press data and places it in a buffer.
4. Display: The application retrieves this data and updates the screen to display the character.