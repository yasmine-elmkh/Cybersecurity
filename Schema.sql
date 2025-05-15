

-- 1. Capture sessions  
CREATE TABLE capture_session (  
  session_id     SERIAL PRIMARY KEY,  
  interface_name VARCHAR(50) NOT NULL,  
  filter_exp      TEXT,                       -- e.g., "tcp port 80"  
  started_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),  
  ended_at        TIMESTAMPTZ  
);  

-- 2. Individual packets  
CREATE TABLE packet (  
  packet_id     BIGSERIAL PRIMARY KEY,  
  session_id    INT NOT NULL REFERENCES capture_session(session_id),  
  captured_at   TIMESTAMPTZ NOT NULL,  
  src_ip        INET NOT NULL,  
  dst_ip        INET NOT NULL,  
  src_port      INT,  
  dst_port      INT,  
  protocol      VARCHAR(20) NOT NULL,          -- e.g., 'TCP','UDP','ICMP'  
  length        INT,  
  raw_data      BYTEA,                         -- optional payload storage  
  CONSTRAINT fk_session
    FOREIGN KEY(session_id) 
    REFERENCES capture_session(session_id) 
    ON DELETE CASCADE  
);  

-- 3. Protocol‐level aggregates  
CREATE TABLE protocol_stats (  
  stats_id      SERIAL PRIMARY KEY,  
  session_id    INT NOT NULL REFERENCES capture_session(session_id),  
  protocol      VARCHAR(20) NOT NULL,  
  packet_count  BIGINT DEFAULT 0,  
  byte_count    BIGINT DEFAULT 0  
);  

-- 4. Detected anomalies  
CREATE TABLE anomaly (  
  anomaly_id    SERIAL PRIMARY KEY,  
  packet_id     BIGINT REFERENCES packet(packet_id),  
  session_id    INT NOT NULL REFERENCES capture_session(session_id),  
  anomaly_type  VARCHAR(50) NOT NULL,         -- e.g., 'SYN_FLOOD','MALFORMED_IP'  
  description   TEXT,  
  detected_at   TIMESTAMPTZ DEFAULT NOW(),  
  ip            VARCHAR(50) NOT NULL,
  CONSTRAINT fk_pkt
    FOREIGN KEY(packet_id) 
    REFERENCES packet(packet_id) 
    ON DELETE SET NULL  
);  

