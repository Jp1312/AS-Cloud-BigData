const restify = require('restify');
const { BotFrameworkAdapter, MemoryStorage, ConversationState } = require('botbuilder');
const axios = require('axios');
const faq = require('../faq.json'); // Importa o JSON da raiz

// Cria o servidor Restify
const server = restify.createServer();
server.listen(3978, () => {
    console.log(`Servidor está no ar em http://localhost:3978`);
});

// Configura o adaptador do Bot Framework
const adapter = new BotFrameworkAdapter({
    appId: '',
    appPassword: ''
});

// Configura estado de conversa em memória
const memoryStorage = new MemoryStorage();
const conversationState = new ConversationState(memoryStorage);
adapter.use(conversationState);

const dialogState = conversationState.createProperty('dialogState');

// Rota do bot
server.post('/api/messages', (req, res) => {
    adapter.processActivity(req, res, async (context) => {
        if (context.activity.type === 'message') {
            const state = await dialogState.get(context, { etapa: 0 });
            const texto = context.activity.text.toLowerCase();

            if (state.etapa === 0) {
                if (faq[texto]) {
                    await context.sendActivity(faq[texto]);
                } else if (texto.includes('matricula') || texto.includes('matrícula')) {
                    state.etapa = 1;
                    await context.sendActivity("Vamos iniciar a matrícula. Qual seu nome?");
                } else {
                    await context.sendActivity("Desculpe, não entendi. Tente perguntar outra coisa ou digite 'matrícula'.");
                }
            } else if (state.etapa === 1) {
                state.nome = texto;
                state.etapa = 2;
                await context.sendActivity("Qual seu e-mail?");
            } else if (state.etapa === 2) {
                state.email = texto;
                state.etapa = 3;
                await context.sendActivity("Qual curso deseja se matricular?");
            } else if (state.etapa === 3) {
                state.curso = texto;

                try {
                    await axios.post('http://localhost:8080/api/matriculas', {
                        nome: state.nome,
                        email: state.email,
                        curso: state.curso
                    });
                    await context.sendActivity("✅ Matrícula realizada com sucesso!");
                } catch (error) {
                    console.error(error);
                    await context.sendActivity("❌ Erro ao enviar dados para o backend.");
                }

                state.etapa = 0; // reinicia
            }

            await conversationState.saveChanges(context);
        }
    });
});
