INSERT INTO app_users (app_user_id, username, email, password, level, xp, profile_image, is_verified, created_at)
VALUES
    (gen_random_uuid(), 'john_doe', 'john@example.com', '123456', 1, 100, 'john.png', true, NOW()),
    (gen_random_uuid(), 'sreynet', 'sreynet@example.com', '123456', 2, 200, 'sreynet.png', true, NOW());

INSERT INTO achievements (achievement_id, title, description, badge, xp_required)
VALUES
    (gen_random_uuid(), 'Starter', 'Complete first habit', 'badge1.png', 50),
    (gen_random_uuid(), 'Pro', 'Complete 10 habits', 'badge2.png', 200);

INSERT INTO app_user_achievements (app_user_id, achievement_id)
SELECT u.app_user_id, a.achievement_id
FROM app_users u, achievements a
LIMIT 2;

INSERT INTO habits (habit_id, title, description, frequency, is_active, app_user_id, created_at)
SELECT
    gen_random_uuid(),
    'Exercise',
    'Morning workout',
    'DAILY',
    true,
    app_user_id,
    NOW()
FROM app_users
LIMIT 2;

INSERT INTO habit_logs (habit_log_id, log_date, status, xp_earned, habit_id)
SELECT
    gen_random_uuid(),
    CURRENT_DATE,
    'COMPLETED',
    10,
    habit_id
FROM habits
LIMIT 2;