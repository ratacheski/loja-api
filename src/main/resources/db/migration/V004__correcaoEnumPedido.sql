UPDATE pedido set status = 4 WHERE status = 3;
UPDATE pedido set status = 3 WHERE status = 2;
UPDATE pedido set status = 2 WHERE status = 1;
UPDATE pedido set status = 1 WHERE status = 0;
UPDATE pedido set forma_pagamento = 4 WHERE forma_pagamento = 3;
UPDATE pedido set forma_pagamento = 3 WHERE forma_pagamento = 2;
UPDATE pedido set forma_pagamento = 2 WHERE forma_pagamento = 1;
UPDATE pedido set forma_pagamento = 1 WHERE forma_pagamento = 0;