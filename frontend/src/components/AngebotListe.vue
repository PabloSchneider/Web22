<template>

<input type="text" v-model="eingabe"/><br>
<button v-on:click="clear">clear</button><br>
<p v-for="angebot in filteredAngebote">
    <AngebotListeItem :a="angebot"></AngebotListeItem>
</p>

<button v-on:click="bietenSimulieren">bietenSimulieren</button>

</template>


<script setup lang="ts">

import AngebotListeItem from '@/components/AngebotListeItem.vue';
import { useFakeAngebot } from '@/services/useFakeAngebot';
import { useAngebot } from '@/services/useAngebot'
import { computed } from '@vue/reactivity';
import { ref } from 'vue';
const {angebote} = useAngebot();
const {bietenSimulieren} = useFakeAngebot();
const eingabe = ref("");

const filteredAngebote = computed(
    ()=> angebote.angebotliste.filter(angebot => angebot.abholort.includes(eingabe.value) || angebot.beschreibung.includes(eingabe.value)||angebot.anbietername.includes(eingabe.value)));
console.log(eingabe.value)

function clear(){
    eingabe.value= "";
}
</script>