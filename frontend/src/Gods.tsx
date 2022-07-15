import { Card } from 'antd'
const { Meta } = Card

const demeter = require('./static/images/demeter.png')
const minotaur = require('./static/images/minotaur.png')
const apollo = require('./static/images/apollo.png')
const pan = require('./static/images/pan.png')
const hephaestus = require('./static/images/hephaestus.jpg')
const artemis = require('./static/images/artemis.jpg')
const atlas = require('./static/images/atlas.jpg')

export const cardData: CardInfo[] = [
  { title: 'Demeter', description: 'Your worker may build one additional time, but not on the same space', path: demeter },
  {
    title: 'Minotaur',
    description: 'Your Move: Your Worker may move into an opponent Worker’s space, ' +
  'if their Worker can be forced one space straight backwards to an unoccupied space at any level.',
    path: minotaur
  },
  { title: 'Pan', description: 'Your Win: You also win if your Worker moves down two or more levels.', path: pan },
  {
    title: 'Apollo',
    description: 'Your Move: Your Worker may move into an opponent Worker’s space by' +
  'forceing their Worker to the space you just vacated. ',
    path: apollo
  },
  {
    title: 'Hephaestus',
    description: 'Your Worker may build one additional block (not dome) on top of your first block.',
    path: hephaestus
  },
  {
    title: 'Artemis',
    description: 'Your Move: Your Worker may move one additional time, but not back to its initial space.',
    path: artemis
  },
  {
    title: 'Atlas',
    description: 'Your Build: Your Worker may build a dome at any level.',
    path: atlas
  }
]

export interface CardInfo {
  title: string
  description: string
  path: string
}

export interface GodCardProps {
  cardInfo: CardInfo
  onSelect: (god: string) => void
}

export function GodCard ({ cardInfo, onSelect }: GodCardProps) {
  // const pic = require(path);
  const cardStyle = { width: 200, height: 500 }

  return (
    <Card
      style={cardStyle}
      cover={
        <img
          alt='example'
          src={cardInfo.path}
          width='220'
          height='250'
        />
}
      bordered
      onClick={() => {
        onSelect(cardInfo.title)
        // setBoarder(true);
      }}
    >
      <Meta title={cardInfo.title} description={cardInfo.description} />
    </Card>
  )
}
