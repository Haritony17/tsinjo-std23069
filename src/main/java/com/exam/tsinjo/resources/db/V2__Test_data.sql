-- Insertion de donateurs
INSERT INTO donor (id, full_name, email, created_at) VALUES
                                                         ('11111111-1111-1111-1111-111111111111', 'Jean Dupont', 'jean.dupont@hei.school', '2023-01-01 10:00:00'),
                                                         ('22222222-2222-2222-2222-222222222222', 'Marie Martin', 'marie.martin@hei.school', '2023-01-02 11:00:00'),
                                                         ('33333333-3333-3333-3333-333333333333', 'Pierre Durand', 'pierre.durand@hei.school', '2023-01-03 12:00:00');

-- Insertion de bénéficiaires
INSERT INTO beneficiary (id, full_name, email, created_at) VALUES
                                                               ('44444444-4444-4444-4444-444444444444', 'Lucie Lambert', 'lucie.lambert@hei.school', '2023-01-05 14:00:00'),
                                                               ('55555555-5555-5555-5555-555555555555', 'Thomas Moreau', 'thomas.moreau@hei.school', '2023-01-06 15:00:00');

-- Insertion de paiements
INSERT INTO payment (id, donor_id, psp_payment_id, psp_type, amount, status, creation_instant, last_psp_verification_instant, verification_attempt_nb) VALUES
                                                                                                                                                           ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', 'PAY123456789', 'ORANGE_MONEY', 10000, 'SUCCEEDED', '2023-01-10 09:00:00', '2023-01-10 09:05:00', 1),
                                                                                                                                                           ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222', 'PAY987654321', 'ORANGE_MONEY', 20000, 'SUCCEEDED', '2023-01-11 10:00:00', '2023-01-11 10:05:00', 1),
                                                                                                                                                           ('cccccccc-cccc-cccc-cccc-cccccccccccc', '33333333-3333-3333-3333-333333333333', 'PAY555555555', 'ORANGE_MONEY', 15000, 'VERIFYING', '2023-01-12 11:00:00', '2023-01-12 11:00:00', 0);

-- Insertion de dons
INSERT INTO donation (id, donor_id, payment_id, donor_name, donor_email, amount, psp_payment_id, status, created_at) VALUES
                                                                                                                         ('dddddddd-dddd-dddd-dddd-dddddddddddd', '11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Jean Dupont', 'jean.dupont@hei.school', 10000, 'PAY123456789', 'SUCCEEDED', '2023-01-10 09:01:00'),
                                                                                                                         ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', '22222222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Marie Martin', 'marie.martin@hei.school', 20000, 'PAY987654321', 'SUCCEEDED', '2023-01-11 10:01:00'),
                                                                                                                         ('ffffffff-ffff-ffff-ffff-ffffffffffff', '33333333-3333-3333-3333-333333333333', 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'Pierre Durand', 'pierre.durand@hei.school', 15000, 'PAY555555555', 'VERIFYING', '2023-01-12 11:01:00');

-- Insertion d'aides
INSERT INTO help (id, beneficiary_id, beneficiary_name, beneficiary_email, amount, accident_description, created_at) VALUES
                                                                                                                         ('66666666-6666-6666-6666-666666666666', '44444444-4444-4444-4444-444444444444', 'Lucie Lambert', 'lucie.lambert@hei.school', 5000, 'Accident de vélo', '2023-01-15 14:00:00'),
                                                                                                                         ('77777777-7777-7777-7777-777777777777', '55555555-5555-5555-5555-555555555555', 'Thomas Moreau', 'thomas.moreau@hei.school', 10000, 'Frais médicaux', '2023-01-16 15:00:00');