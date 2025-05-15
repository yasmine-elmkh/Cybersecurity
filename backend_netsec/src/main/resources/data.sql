DELETE FROM anomaly;

-- Two sniffing sessions on different interfaces with filters and timestamps
INSERT INTO capture_session (session_id, interface_name, filter_exp, started_at, ended_at) VALUES
(1, 'eth0',  'tcp port 80',  '2025-04-28 09:00:00+00', '2025-04-28 09:30:00+00'),
(2, 'wlan0', 'udp port 53',  '2025-04-28 10:15:00+00', '2025-04-28 10:45:00+00');

-- Packets for session 1 (mostly HTTP/TCP on port 80)
INSERT INTO packet (packet_id, session_id, captured_at, src_ip, dst_ip, src_port, dst_port, protocol, length, raw_data) VALUES
(1, 1, '2025-04-28 09:01:05+00', '192.168.1.10', '93.184.216.34', 54321,    80,       'TCP',    60,     '\x4500003c1c4640004006b1e6ac100a63ac100a0c'),
(2, 1, '2025-04-28 09:01:06+00', '93.184.216.34', '192.168.1.10', 80,       54321,    'TCP',    52,     '\x45000028000100004011a6ecc0a8010ac0a80101'),
(3, 1, '2025-04-28 09:15:22+00', '192.168.1.10', '93.184.216.34', 54322,    80,       'TCP',    60,     '\x4500003c1c4740004006b1e5ac100a63ac100a0c'),
(4, 1, '2025-04-28 09:15:23+00', '93.184.216.34', '192.168.1.10', 80,       54322,    'TCP',    52,     '\x45000028000200004011a6ebc0a8010ac0a80101');

-- Packets for session 2 (DNS over UDP on port 53, plus one extra ICMP ping)
INSERT INTO packet (packet_id, session_id, captured_at, src_ip, dst_ip, src_port, dst_port, protocol, length, raw_data) VALUES
(5, 2, '2025-04-28 10:16:10+00', '192.168.1.15', '8.8.8.8',     55555,    53,       'UDP',    74,     '\x4500003ea6f400004011b861c0a8010fc0a80101'),
(6, 2, '2025-04-28 10:16:10+00', '8.8.8.8',     '192.168.1.15', 53,       55555,    'UDP',    90,     '\x45000054a6f500004011b860c0a8010fc0a80101'),
(7, 2, '2025-04-28 10:20:00+00', '192.168.1.15', '8.8.8.8',     NULL,     NULL,     'ICMP',   98,     '\x45000054000100004001a7dac0a8010fc0a80101');

-- Summaries: count and total bytes per protocol for each session
INSERT INTO protocol_stats (stats_id, session_id, protocol, packet_count, byte_count) VALUES
(1, 1, 'TCP', 4, 224),   -- 4 TCP packets totaling (60+52+60+52)=224 bytes
(2, 2, 'UDP', 2, 164),   -- 2 UDP packets totaling (74+90)=164 bytes
(3, 2, 'ICMP', 1, 98);   -- 1 ICMP packet totaling 98 bytes

-- Flag a potential SYN flood (multiple SYNs without corresponding ACKs)
INSERT INTO anomaly (anomaly_id, packet_id, session_id, anomaly_type,    description,      detected_at ,ip) VALUES
(1,  3,           1,          'SYN_FLOOD',   'Multiple SYN packets with no ACK replies in session 1','2025-04-28 09:16:00+00','192.168.1.1');
-- -- Flag a malformed packet (e.g. bad checksum or truncated)
-- (2,  6,           2,          'MALFORMED_UDP','UDP packet with invalid length field',           '2025-04-28 10:16:11+00'),
-- -- Session‐level alert (no specific packet)
-- (3,  NULL,        2,          'HIGH_ICMP_RATE','Excessive ICMP traffic detected (>10 packets/min)',   '2025-04-28 10:25:00+00');

INSERT INTO users (id, username, password, enabled) VALUES (1, 'admin', 'admin', true);
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
