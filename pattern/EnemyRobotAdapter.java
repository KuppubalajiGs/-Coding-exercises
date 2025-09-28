public class EnemyRobotAdapter implements EnemyAttacker {
    private final EnemyRobot robot;
    public EnemyRobotAdapter(EnemyRobot r) { this.robot = r; }
    @Override public void fireWeapon() { robot.smashWithHands(); }
    @Override public void driveForward() { robot.walkForward(); }
    @Override public void assignDriver(String driverName) { robot.reactToHuman(driverName); }
}