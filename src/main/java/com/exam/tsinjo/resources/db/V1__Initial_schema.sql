-- Table Donor (Donateur)
CREATE TABLE donor (
                       id UUID PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table Beneficiary (Bénéficiaire)
CREATE TABLE beneficiary (
                             id UUID PRIMARY KEY,
                             full_name VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL UNIQUE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table Payment (Paiement)
CREATE TABLE payment (
                         id UUID PRIMARY KEY,
                         donor_id UUID NOT NULL REFERENCES donor(id),
                         psp_payment_id VARCHAR(255) NOT NULL UNIQUE,
                         psp_type VARCHAR(50) NOT NULL CHECK (psp_type IN ('ORANGE_MONEY')),
                         amount INTEGER NOT NULL CHECK (amount > 0),
                         status VARCHAR(20) NOT NULL CHECK (status IN ('VERIFYING', 'SUCCEEDED', 'FAILED')),
                         creation_instant TIMESTAMP NOT NULL,
                         last_psp_verification_instant TIMESTAMP,
                         verification_attempt_nb INTEGER DEFAULT 0,
                         CONSTRAINT unique_psp_payment UNIQUE (psp_payment_id)
);

-- Table Donation (Don)
CREATE TABLE donation (
                          id UUID PRIMARY KEY,
                          donor_id UUID NOT NULL REFERENCES donor(id),
                          payment_id UUID NOT NULL REFERENCES payment(id),
                          donor_name VARCHAR(255) NOT NULL,
                          donor_email VARCHAR(255) NOT NULL,
                          amount DOUBLE PRECISION NOT NULL,
                          psp_payment_id VARCHAR(255) NOT NULL,
                          status VARCHAR(20) NOT NULL CHECK (status IN ('VERIFYING', 'SUCCEEDED', 'FAILED')),
                          created_at TIMESTAMP NOT NULL,
                          CONSTRAINT fk_payment FOREIGN KEY (payment_id) REFERENCES payment(id)
);

-- Table Help (Aide)
CREATE TABLE help (
                      id UUID PRIMARY KEY,
                      beneficiary_id UUID REFERENCES beneficiary(id),
                      beneficiary_name VARCHAR(255) NOT NULL,
                      beneficiary_email VARCHAR(255) NOT NULL,
                      amount DOUBLE PRECISION NOT NULL CHECK (amount > 0),
                      accident_description TEXT NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      CONSTRAINT fk_beneficiary FOREIGN KEY (beneficiary_id) REFERENCES beneficiary(id)
);

-- Index pour améliorer les performances
CREATE INDEX idx_payment_status ON payment(status);
CREATE INDEX idx_payment_donor ON payment(donor_id);
CREATE INDEX idx_donation_payment ON donation(payment_id);
CREATE INDEX idx_donation_donor ON donation(donor_id);
CREATE INDEX idx_help_beneficiary ON help(beneficiary_id);