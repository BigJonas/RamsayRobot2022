package frc.robot.util;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Key extends Trigger{
    
    private final Keyboard board;
    private final int id;

    public boolean isPressed;

    public Key(Keyboard board, int id) {

        this.board = board;
        this.id = id;

        isPressed = false;

    }

    public boolean get() {

        return board.getKeyPressed(id);

    }

    /**
     * Starts the given command whenever the key is newly pressed.
     *
     * @param command the command to start
     * @param interruptible whether the command is interruptible
     * @return this key, so calls can be chained
     */

    public Key whenPressed(final Command command, boolean interruptible) {
        whenActive(command, interruptible);
        return this;
    }

    /**
     * Starts the given command whenever the key is newly pressed. The command is set to be
     * interruptible.
     *
     * @param command the command to start
     * @return this key, so calls can be chained
     */
    public Key whenPressed(final Command command) {
        whenActive(command);
        return this;
    }

    /**
     * Runs the given runnable whenever the key is newly pressed.
     *
     * @param toRun the runnable to run
     * @param requirements the required subsystems
     * @return this key, so calls can be chained
     */
    public Key whenPressed(final Runnable toRun, Subsystem... requirements) {
        whenActive(toRun, requirements);
        return this;
    }

    /**
     * Constantly starts the given command while the key is held.
     *
     * <p>{@link Command#schedule(boolean)} will be called repeatedly while the key is held, and
     * will be canceled when the key is released.
     *
     * @param command the command to start
     * @param interruptible whether the command is interruptible
     * @return this key, so calls can be chained
     */
    public Key whileHeld(final Command command, boolean interruptible) {
    whileActiveContinuous(command, interruptible);
    return this;
    }

    /**
     * Constantly starts the given command while the key is held.
     *
     * <p>{@link Command#schedule(boolean)} will be called repeatedly while the key is held, and
     * will be canceled when the key is released. The command is set to be interruptible.
     *
     * @param command the command to start
     * @return this key, so calls can be chained
     */
    public Key whileHeld(final Command command) {
        whileActiveContinuous(command);
        return this;
    }

    /**
     * Constantly runs the given runnable while the key is held.
     *
     * @param toRun the runnable to run
     * @param requirements the required subsystems
     * @return this key, so calls can be chained
     */
    public Key whileHeld(final Runnable toRun, Subsystem... requirements) {
        whileActiveContinuous(toRun, requirements);
        return this;
    }

    /**
     * Starts the given command when the key is first pressed, and cancels it when it is released,
     * but does not start it again if it ends or is otherwise interrupted.
     *
     * @param command the command to start
     * @param interruptible whether the command is interruptible
     * @return this key, so calls can be chained
     */
    public Key whenHeld(final Command command, boolean interruptible) {
        whileActiveOnce(command, interruptible);
        return this;
    }

    /**
     * Starts the given command when the key is first pressed, and cancels it when it is released,
     * but does not start it again if it ends or is otherwise interrupted. The command is set to be
     * interruptible.
     *
     * @param command the command to start
     * @return this key, so calls can be chained
     */
    public Key whenHeld(final Command command) {
        whileActiveOnce(command, true);
        return this;
    }

    /**
     * Starts the command when the key is released.
     *
     * @param command the command to start
     * @param interruptible whether the command is interruptible
     * @return this key, so calls can be chained
     */
    public Key whenReleased(final Command command, boolean interruptible) {
        whenInactive(command, interruptible);
        return this;
    }

    /**
     * Starts the command when the key is released. The command is set to be interruptible.
     *
     * @param command the command to start
     * @return this key, so calls can be chained
     */
    public Key whenReleased(final Command command) {
        whenInactive(command);
        return this;
    }

    /**
     * Runs the given runnable when the key is released.
     *
     * @param toRun the runnable to run
     * @param requirements the required subsystems
     * @return this key, so calls can be chained
     */
    public Key whenReleased(final Runnable toRun, Subsystem... requirements) {
    whenInactive(toRun, requirements);
    return this;
    }

    /**
     * Toggles the command whenever the key is pressed (on then off then on).
     *
     * @param command the command to start
     * @param interruptible whether the command is interruptible
     * @return this key, so calls can be chained
     */
    public Key toggleWhenPressed(final Command command, boolean interruptible) {
        toggleWhenActive(command, interruptible);
        return this;
    }

    /**
     * Toggles the command whenever the key is pressed (on then off then on). The command is set to
     * be interruptible.
     *
     * @param command the command to start
     * @return this key, so calls can be chained
     */
    public Key toggleWhenPressed(final Command command) {
        toggleWhenActive(command);
        return this;
    }

    /**
     * Cancels the command when the key is pressed.
     *
     * @param command the command to start
     * @return this key, so calls can be chained
     */
    public Key cancelWhenPressed(final Command command) {
        cancelWhenActive(command);
        return this;
    }

}
