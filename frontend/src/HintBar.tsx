
import { Avatar, Space } from 'antd'

/**
 * Defines the hint bar above the board.
 * This give user hints on color meaning.
 * @returns HintBar functional component
 */
export default function HintBar () {
  return (
    <Space size={8}>
      <Avatar
        style={{
          backgroundColor: 'lightcoral',
          verticalAlign: 'middle'
        }} size='large'
      />
      Target Cells

      <Avatar
        style={{
          backgroundColor: 'aquamarine',
          verticalAlign: 'middle'
        }} size='large'
      />
      Possible Actions

      <Avatar
        style={{
          backgroundColor: 'dodgerblue',
          verticalAlign: 'middle'
        }} size='large'
      />
      Selected Worker

    </Space>
  )
}
